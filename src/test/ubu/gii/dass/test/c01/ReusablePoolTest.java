/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool contenedor = null;
		assertNull(contenedor);
		assertNotNull(contenedor.getInstance());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException {
		ReusablePool cont2 = ReusablePool.getInstance();
		Reusable r1,r2 = null;
		
		r1 = cont2.acquireReusable();
		assertNotNull(r1);
		r2 = cont2.acquireReusable();
		assertNotNull(r2);
		//este método devuelve una excepcion
		//assertError(cont2.acquireReusable());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		ReusablePool cont3 = ReusablePool.getInstance();
		Reusable r1,r2 = null;
		r1 = cont3.acquireReusable();
		String r1util = r1.util(); //guardamos su r1.util()
		assertNotNull(r1);
		cont3.releaseReusable(r1);//si volvemos a crear otro deberia coincidir so .til
		r2 = cont3.acquireReusable();
		assertNotNull(r1);
		//Assert.assertEquals(r1util, r2.util());
		cont3.releaseReusable(r2);
		//si tratamos de eleminar r1 que ya esta eliminado deberia de saltar una excepción
		//Assert.assertNotEquals(r1.util(), r2.util());
	}

}
