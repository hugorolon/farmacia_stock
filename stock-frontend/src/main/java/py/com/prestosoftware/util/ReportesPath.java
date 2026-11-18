package py.com.prestosoftware.util;

import java.io.File;

public final class ReportesPath {

    private ReportesPath() {
    }

    public static File resolver() {
        File current = new File(System.getProperty("user.dir"));

        for (File directory = current.getAbsoluteFile(); directory != null; directory = directory.getParentFile()) {
            File reportes = new File(directory, "reportes");
            if (reportes.isDirectory()) {
                return reportes;
            }

            File binReportes = new File(directory, "bin/reportes");
            if (binReportes.isDirectory()) {
                return binReportes;
            }

            // Cuando se ejecuta desde la raiz del repositorio (p. ej. desde VS Code)
            File moduloReportes = new File(directory, "stock-frontend/reportes");
            if (moduloReportes.isDirectory()) {
                return moduloReportes;
            }
        }

        return new File(current, "reportes");
    }
}
