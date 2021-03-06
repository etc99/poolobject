package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;


public class ReusablePoolTest {
	
	ReusablePool contenedor = null;
	Reusable r1,r2,r3;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		contenedor = ReusablePool.getInstance();
		r1 = null;
		r2 = null;
		r3 = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		contenedor = null;
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
	 */
	@Test
	public void testAcquireReusable()  {
		try {
			r1 = contenedor.acquireReusable();
			assertNotNull(r1);
			r2 = contenedor.acquireReusable();
			assertNotNull(r2);
		}catch(NotFreeInstanceException e1) {
			fail("Fallo por la Excepcion NotFreeInstanceException");
		}		
		try {
			r3 = contenedor.acquireReusable();
		}catch(NotFreeInstanceException e) {
			r3 = null; 
		}
		assertNull(r3); 
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable(){
		try {
			r1 = contenedor.acquireReusable();
			String r1util = r1.util(); 
			assertNotNull(r1);
			contenedor.releaseReusable(r1);
			r2 = contenedor.acquireReusable();
			assertNotNull(r1);
			assertEquals(r1util, r2.util());
			contenedor.releaseReusable(r2);
		}catch(NotFreeInstanceException e1) {
			fail("Fallo por la Excepcion NotFreeInstanceException");
		}catch(DuplicatedInstanceException e2) {
			fail("Fallo por la Excepcion DuplicatedInstanceException");
		}
		try {
			contenedor.releaseReusable(r1); 
		}catch(DuplicatedInstanceException e3) {
			assertEquals(e3.getMessage(),"Ya existe esa instancia en el pool.");
		}
		
	}

}
