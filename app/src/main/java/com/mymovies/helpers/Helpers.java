package com.mymovies.helpers;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Helpers {

    public static <T> T getGenericOrDefault(JsonObject jsonObject, String key, T defaultValue) {
        return tryOrDefault(() -> {
            T internalValue = defaultValue;
            JsonElement element = jsonObject.get(key);
            if (element != null) {
                if (!element.isJsonNull() && element.isJsonPrimitive()) {
                    JsonPrimitive primitiveValue = element.getAsJsonPrimitive();
                    if (primitiveValue.isBoolean())
                        internalValue = convertInstanceOfObject(primitiveValue.getAsBoolean());
                    else if (primitiveValue.isNumber())
                        internalValue = convertInstanceOfObject(primitiveValue.getAsNumber());
                    else if (primitiveValue.isString())
                        internalValue = convertInstanceOfObject(primitiveValue.getAsString());
                    else internalValue = convertInstanceOfObject(element);
                }
            }
            return internalValue;
        }, defaultValue);
    }

    private static <T> T tryOrDefault(GenericSupplier<T> function, T defaultValue) {
        T value = defaultValue;
        try {
            value = function.invoke();
        } catch (Exception e) {
            Log.d("TRY_OR_DEFAULT", e.getLocalizedMessage());
        }
        return value;
    }


    @SuppressWarnings("unchecked")
    private static <T> T convertInstanceOfObject(Object object) {
        try {
            return (T) object;
        } catch (ClassCastException e) {
            return null;
        }
    }

}

interface GenericSupplier<T> {
    T invoke();
}
