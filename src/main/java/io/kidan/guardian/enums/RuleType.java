package io.kidan.guardian.enums;

public enum RuleType {
    PAGE_COUNT_RULE, SCHEMA_RULE, KEYWORD_RULE, RANGE, FORMAT_TYPE;

    public String initCap() {
        switch (this) {
            case PAGE_COUNT_RULE -> {
                return "Page count rule";
            }
            case SCHEMA_RULE -> {
                return "Schema rule";
            }
            case KEYWORD_RULE -> {
                return "Key word";
            }
            case RANGE -> {
                return "Range";
            }
            case FORMAT_TYPE -> {
                return "Format type";
            }
            default -> {
                return "Unknown";
            }
        }
    }
}
