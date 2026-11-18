package py.com.prestosoftware.client;

public final class FrontendApiClientSupport {

    private FrontendApiClientSupport() {
    }

    public static boolean nombreContains(Object entity, String normalizedNombre) {
        if (entity == null || normalizedNombre == null) {
            return false;
        }

        try {
            Object nombre = entity.getClass().getMethod("getNombre").invoke(entity);
            return nombre != null && nombre.toString().toLowerCase().contains(normalizedNombre.toLowerCase());
        } catch (ReflectiveOperationException exception) {
            return false;
        }
    }
}
