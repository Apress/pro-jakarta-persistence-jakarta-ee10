package examples.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToIntegerConverter implements AttributeConverter<Boolean,Integer> {
	
    public Integer convertToDatabaseColumn(Boolean attrib) {
        return (attrib ? 1 : 0);
    }

    public Boolean convertToEntityAttribute(Integer dbData) {
        return (dbData > 0); 
    }
}
