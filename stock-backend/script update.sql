ALTER TABLE IF EXISTS public.ventas
    ADD COLUMN IF NOT EXISTS timbrado bigint;

-- =====================================================================
-- CONTROL DE LOTES (farmacia) - compras, ventas e informes
-- Script idempotente: se puede ejecutar mas de una vez.
-- =====================================================================

-- Tabla de lotes (ya existe en la base actual; se crea solo si falta)
CREATE TABLE IF NOT EXISTS public.lotes (
    id                serial PRIMARY KEY,
    id_producto       bigint NOT NULL REFERENCES public.productos(id),
    fecha_vencimiento timestamp without time zone NOT NULL,
    precio_compra     double precision,
    stock_actual      double precision,
    codigo_lote       character varying(30),
    cantidad_inicial  double precision,
    fecha_ingreso     timestamp with time zone
);

-- Los codigos de lote de laboratorio suelen superar 10 caracteres
ALTER TABLE public.lotes ALTER COLUMN codigo_lote TYPE character varying(30);

CREATE INDEX IF NOT EXISTS idx_lotes_id_producto ON public.lotes (id_producto);
CREATE INDEX IF NOT EXISTS idx_lotes_vencimiento ON public.lotes (fecha_vencimiento);

-- Lote ingresado en cada item de compra
ALTER TABLE public.compra_detalles ADD COLUMN IF NOT EXISTS lote_id bigint;
ALTER TABLE public.compra_detalles ADD COLUMN IF NOT EXISTS codigo_lote character varying(30);
ALTER TABLE public.compra_detalles ADD COLUMN IF NOT EXISTS fecha_vencimiento timestamp without time zone;

-- Kardex de lotes: entradas (compras) y salidas (ventas FEFO) con su referencia
CREATE TABLE IF NOT EXISTS public.lote_movimientos (
    id            bigserial PRIMARY KEY,
    lote_id       bigint NOT NULL,
    producto_id   bigint NOT NULL,
    tipo          character varying(20) NOT NULL,  -- COMPRA | VENTA | ANULA_COMPRA | ANULA_VENTA
    referencia_id bigint,                          -- id de compra o venta
    cantidad      double precision NOT NULL,       -- + entrada / - salida
    fecha         timestamp without time zone NOT NULL DEFAULT now(),
    usuario_id    bigint,
    revertido     integer NOT NULL DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_lote_mov_lote ON public.lote_movimientos (lote_id);
CREATE INDEX IF NOT EXISTS idx_lote_mov_ref ON public.lote_movimientos (tipo, referencia_id);
CREATE INDEX IF NOT EXISTS idx_lote_mov_producto ON public.lote_movimientos (producto_id);
