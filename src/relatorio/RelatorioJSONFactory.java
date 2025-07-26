package relatorio;

public class RelatorioJSONFactory extends RelatorioFactory {
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioJSON();
    }
}