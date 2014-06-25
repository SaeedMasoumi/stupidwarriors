/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed RajabZade.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import mahyarise.judge.GameManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bl
 */
public class JudgeTest {
    private GameManager judgeTest ;
    public JudgeTest() {
    judgeTest.setMapSize(200, 200);
        int[][] a = new int [200][200];
        for(int i=0;i<200;i++)
            for(int j = 0 ;j<200;j++)
                a[i][j] = 10;
        judgeTest.loadMap(a);
    }
    @Test
    public void Test(){
        assertEquals(2100, judgeTest.getMapHeight());
        
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    
    }
    
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
