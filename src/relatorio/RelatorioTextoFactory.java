package relatorio;

public class RelatorioTextoFactory extends RelatorioFactory {
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioTexto();
    }
}