
/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sola.services.common.test.AbstractEJBTest;
import org.sola.services.ejb.bulkload.businesslogic.BulkLoadEJB;
import org.sola.services.ejb.bulkload.businesslogic.BulkLoadEJBLocal;

/**
 *
 * @author Andrew
 */
public class BulkLoadEJBIT extends AbstractEJBTest {

    private static String EJB_MODULE_NAME = "sola-bulk-load-1.2-SNAPSHOT";

    public BulkLoadEJBIT() {
        super();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadSlrSource() throws Exception {
        BulkLoadEJBLocal instance = (BulkLoadEJBLocal) getEJBInstance(EJB_MODULE_NAME,
                BulkLoadEJB.class.getSimpleName());
        //getUserTransaction().begin();
        //String result = instance.loadDocuments("deedOfSublease",
         //       "C:\\Work\\SOLA\\Tonga\\Scanning\\Subleases");

//System.out.println(instance.getRefNumber("the;folder;DSL-123-3.pdf"));
//System.out.println(instance.getRefNumber("DSL-123-4.pdf"));
        // getUserTransaction().commit();
                
                

    }
}