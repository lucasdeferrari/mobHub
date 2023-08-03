package domain.generadorRankings;

public class EstrategiaExportacion {
    public interface EstrategiaDeExportacion {
        public String exportar(Exportable exportable);
    }
}
