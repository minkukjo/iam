package me.harry.iam.domain.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

public enum Type {
    COMMUNITY(100, "community"),
    STUDY(200, "study"),
    QNA(300, "qna");

    private final int value;
    private final String symbol;

    Type(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    @JsonCreator
    public static Type fromString(String symbol) {
        return Stream.of(Type.values())
                .filter(c -> c.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @JsonValue
    public String getSymbol() {
        return symbol;
    }


    public static class TypeJpaConverter implements AttributeConverter<Type, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Type attribute) {
            return attribute.value;
        }

        @Override
        public Type convertToEntityAttribute(Integer dbData) {
            return Stream.of(Type.values())
                    .filter(c -> c.value == dbData)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

}
