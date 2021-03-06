// To use this code, add the following Maven dependency to your project:
//
//     com.fasterxml.jackson.core : jackson-databind : 2.9.0
//
// Import this package:
//
//     import com.skide.skriptinsight.model.Converter;
//
// Then you can deserialize a JSON string with
//
//     InspectionRequest data = Converter.InspectionRequestFromJsonString(jsonString);
//     Inspection[] data = Converter.InspectionFromJsonString(jsonString);
//     InspectionResult data = Converter.InspectionResultFromJsonString(jsonString);

package com.skide.skriptinsight.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class Converter {
    // Serialize/deserialize helpers

    public static InspectionRequest InspectionRequestFromJsonString(String json) throws IOException {
        return getInspectionRequestObjectReader().readValue(json);
    }

    public static String InspectionRequestToJsonString(InspectionRequest obj) throws JsonProcessingException {
        return getInspectionRequestObjectWriter().writeValueAsString(obj);
    }

    public static Inspection[] InspectionFromJsonString(String json) throws IOException {
        return getInspectionObjectReader().readValue(json);
    }

    public static String InspectionToJsonString(Inspection[] obj) throws JsonProcessingException {
        return getInspectionObjectWriter().writeValueAsString(obj);
    }

    public static InspectionResult InspectionResultFromJsonString(String json) throws IOException {
        return getInspectionResultObjectReader().readValue(json);
    }

    public static String InspectionResultToJsonString(InspectionResult obj) throws JsonProcessingException {
        return getInspectionResultObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader InspectionRequestReader;
    private static ObjectWriter InspectionRequestWriter;

    private static void instantiateInspectionRequestMapper() {
        ObjectMapper mapper = new ObjectMapper();
        InspectionRequestReader = mapper.readerFor(InspectionRequest.class);
        InspectionRequestWriter = mapper.writerFor(InspectionRequest.class);
    }

    private static ObjectReader getInspectionRequestObjectReader() {
        if (InspectionRequestReader == null) instantiateInspectionRequestMapper();
        return InspectionRequestReader;
    }

    private static ObjectWriter getInspectionRequestObjectWriter() {
        if (InspectionRequestWriter == null) instantiateInspectionRequestMapper();
        return InspectionRequestWriter;
    }

    private static ObjectReader InspectionReader;
    private static ObjectWriter InspectionWriter;

    private static void instantiateInspectionMapper() {
        ObjectMapper mapper = new ObjectMapper();
        InspectionReader = mapper.readerFor(Inspection[].class);
        InspectionWriter = mapper.writerFor(Inspection[].class);
    }

    private static ObjectReader getInspectionObjectReader() {
        if (InspectionReader == null) instantiateInspectionMapper();
        return InspectionReader;
    }

    private static ObjectWriter getInspectionObjectWriter() {
        if (InspectionWriter == null) instantiateInspectionMapper();
        return InspectionWriter;
    }

    private static ObjectReader InspectionResultReader;
    private static ObjectWriter InspectionResultWriter;

    private static void instantiateInspectionResultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        InspectionResultReader = mapper.readerFor(InspectionResult.class);
        InspectionResultWriter = mapper.writerFor(InspectionResult.class);
    }

    private static ObjectReader getInspectionResultObjectReader() {
        if (InspectionResultReader == null) instantiateInspectionResultMapper();
        return InspectionResultReader;
    }

    private static ObjectWriter getInspectionResultObjectWriter() {
        if (InspectionResultWriter == null) instantiateInspectionResultMapper();
        return InspectionResultWriter;
    }
}