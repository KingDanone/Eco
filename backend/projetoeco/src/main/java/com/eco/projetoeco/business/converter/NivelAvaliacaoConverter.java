package com.eco.projetoeco.business.converter;

import com.eco.projetoeco.data.model.NivelAvaliacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NivelAvaliacaoConverter implements AttributeConverter<NivelAvaliacao, String> {

    @Override
    public String convertToDatabaseColumn(NivelAvaliacao attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValor();  // retorna "1", "2", etc.
    }

    @Override
    public NivelAvaliacao convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return NivelAvaliacao.fromValor(dbData);
    }
}
