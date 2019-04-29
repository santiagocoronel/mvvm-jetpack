package com.example.jetpack._model.networking;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.util.Date;

public class GsonSerializers {

    public static JsonSerializer<Date> serializerDateToTimeLong = (src, typeOfSrc, context) -> src == null ? null : new JsonPrimitive(src.getTime());

    public static JsonDeserializer<Date> deserializerDateToTimeLong = (json, typeOfT, context) -> json == null ? null : new Date(json.getAsLong());
}