package org.CRUD.Learning.events;

import org.CRUD.Learning.customAnnotation.EncryptedField;
import org.CRUD.Learning.utils.CryptoUtil;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class EncryptionEventListener extends AbstractMongoEventListener<Object> {

    private final CryptoUtil cryptoUtil;

    public EncryptionEventListener() throws Exception {
        this.cryptoUtil = new CryptoUtil("mysecretkey12345");
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        encryptFields(source);
    }

    @Override
    public void onAfterConvert(AfterConvertEvent<Object> event) {
        Object source = event.getSource();
        decryptFields(source);
    }

    private void encryptFields(Object source) {
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(EncryptedField.class)) {
                    field.setAccessible(true);
                    String value = (String) field.get(source);
                    if (value != null) {
                        field.set(source, cryptoUtil.encrypt(value));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting fields", e);
        }
    }

    private void decryptFields(Object source) {
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(EncryptedField.class)) {
                    field.setAccessible(true);
                    String value = (String) field.get(source);
                    if (value != null) {
                        field.set(source, cryptoUtil.decrypt(value));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting fields", e);
        }
    }
}
