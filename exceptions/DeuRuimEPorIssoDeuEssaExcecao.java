package exceptions;

public class DeuRuimEPorIssoDeuEssaExcecao extends RuntimeException {
    private final String mensagem;
    public DeuRuimEPorIssoDeuEssaExcecao(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
