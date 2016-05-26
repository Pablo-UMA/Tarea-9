import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestTablon {

	private TablonDeAnuncios tabl�n;
	 
    @Before
    public void init() {
        tabl�n = new TablonDeAnuncios();
    }
 
    @After
    public void close() {
        tabl�n = null;
    }
 
    @Test
    public void anuncioInicial() {
 
        assertEquals(1, tabl�n.anunciosPublicados());
    }
 
    @Test
    public void nuevoAnuncioEmpresa() {
        int nAnuncios = tabl�n.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tabl�n.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(nAnuncios + 1, tabl�n.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularNoExiste() {
        int numAnuncios = tabl�n.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tabl�n.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(numAnuncios, tabl�n.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularExiste() {
        int numAnuncios = tabl�n.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(true);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
        when(bdPagos.anuncianteTieneSaldo("Juan")).thenReturn(true);
 
        tabl�n.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(numAnuncios + 1, tabl�n.anunciosPublicados());
        verify(bdPagos, times(1)).anuncioPublicado("Juan");
 
    }
 
    @Test
    public void DosAnuncios() {
        int nAnuncios = tabl�n.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tabl�n.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tabl�n.publicarAnuncio(b, bdAnunciantes, bdPagos);
        if (tabl�n.buscarAnuncioPorTitulo(b.getTitulo()).equals(a.getTitulo())) {
            tabl�n.borrarAnuncio(b.getTitulo(), b.getAnunciante());
        }
 
        assertEquals(nAnuncios + 1, tabl�n.anunciosPublicados());
 
    }
 
    @Test
    public void AnuncioQuitado() {
        int numAnuncios = tabl�n.anunciosPublicados();
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo 2", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tabl�n.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tabl�n.publicarAnuncio(b, bdAnunciantes, bdPagos);
 
        tabl�n.borrarAnuncio(a.getTitulo(), a.getAnunciante());
 
        assertEquals(numAnuncios + 1, tabl�n.anunciosPublicados());
    }
 

}
