import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestTablon {

	private TablonDeAnuncios tablón;
	 
    @Before
    public void init() {
        tablón = new TablonDeAnuncios();
    }
 
    @After
    public void close() {
        tablón = null;
    }
 
    @Test
    public void anuncioInicial() {
 
        assertEquals(1, tablón.anunciosPublicados());
    }
 
    @Test
    public void nuevoAnuncioEmpresa() {
        int nAnuncios = tablón.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablón.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(nAnuncios + 1, tablón.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularNoExiste() {
        int numAnuncios = tablón.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablón.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(numAnuncios, tablón.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularExiste() {
        int numAnuncios = tablón.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(true);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
        when(bdPagos.anuncianteTieneSaldo("Juan")).thenReturn(true);
 
        tablón.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(numAnuncios + 1, tablón.anunciosPublicados());
        verify(bdPagos, times(1)).anuncioPublicado("Juan");
 
    }
 
    @Test
    public void DosAnuncios() {
        int nAnuncios = tablón.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablón.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tablón.publicarAnuncio(b, bdAnunciantes, bdPagos);
        if (tablón.buscarAnuncioPorTitulo(b.getTitulo()).equals(a.getTitulo())) {
            tablón.borrarAnuncio(b.getTitulo(), b.getAnunciante());
        }
 
        assertEquals(nAnuncios + 1, tablón.anunciosPublicados());
 
    }
 
    @Test
    public void AnuncioQuitado() {
        int numAnuncios = tablón.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo 2", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablón.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tablón.publicarAnuncio(b, bdAnunciantes, bdPagos);
 
        tablón.borrarAnuncio(a.getTitulo(), a.getAnunciante());
 
        assertEquals(numAnuncios + 1, tablón.anunciosPublicados());
    }
 

}
