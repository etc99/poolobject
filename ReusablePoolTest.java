/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {
	
	ReusablePool contenedor = null;
	Reusable r1,r2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		contenedor = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		contenedor = null;
		r1 = null;
		r2 = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		contenedor = null;
		assertNull(contenedor);
		assertNotNull(contenedor.getInstance());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException {
		
		
		
		r1 = contenedor.acquireReusable();
		assertNotNull(r1);
		r2 = contenedor.acquireReusable();
		assertNotNull(r2);
		//este método devuelve una excepcion
		//assertError(contenedor.acquireReusable());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws NotFreeInstanceException 
	 * @throws DuplicatedInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException, DuplicatedInstanceException {
		
		r1 = contenedor.acquireReusable();
		String r1util = r1.util(); //guardamos su r1.util()
		assertNotNull(r1);
		contenedor.releaseReusable(r1);//si volvemos a crear otro deberia coincidir so .util
		r2 = contenedor.acquireReusable();
		assertNotNull(r1);
		Assert.assertEquals(r1util, r2.util());
		contenedor.releaseReusable(r2);
		//si tratamos de eleminar r1 que ya esta eliminado deberia de saltar una excepción
		
	}

}
