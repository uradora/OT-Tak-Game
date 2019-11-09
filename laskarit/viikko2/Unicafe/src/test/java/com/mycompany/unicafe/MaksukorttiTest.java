package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kassapaate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test 
    public void kortinAlkusaldoOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaRahaa10EuroaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinKunSyoMaukkaasti() {
        kortti.lataaRahaa(1000);
        kassapaate.syoEdullisesti(kortti);
        assertEquals("saldo: 7.70", kortti.toString());
    }
    
    @Test
    public void josRahaaEiOleTarpeeksiSaldoEiMuutu() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void palautaTrueJosRahatRiittivat() {
        kortti.lataaRahaa(1000);
        boolean rahaRiitti = kassapaate.syoEdullisesti(kortti);
        assertTrue(rahaRiitti);
    }
    
    @Test
    public void palautaFalseJosRahatEivatRiittaneet() {
        boolean rahaEiRiittanyt = kassapaate.syoEdullisesti(kortti);
        assertFalse(rahaEiRiittanyt);
    }
}
