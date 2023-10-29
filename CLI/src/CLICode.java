public enum CLICode {
    MAIN_MENU(0),
    NO_ERROR_NO_REPEAT_OP(1),
    CONTINUE(2);

    private final int code;

    CliCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
