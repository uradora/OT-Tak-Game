/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author meriraja
 */
public class KassapaateTest {
    
    private Kassapaate kassapaate;
    private Maksukortti kortti;
    
    @Before
    public void setUp() {
        this.kassapaate = new Kassapaate();
        this.kortti = new Maksukortti(0);
    }

    @Test
    public void kassapaateAloitusRahatOikein() {
        int aloitusRahat = kassapaate.kassassaRahaa();
        assertTrue(aloitusRahat == 100000);
    }
    
    @Test
    public void alussaEiMyytyjaEdullisia() {
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(edullisiaMyyty == 0);
    }
    
    @Test
    public void alussaEiMyytyjaMaukkaita() {
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(maukkaitaMyyty == 0);
    }
    
    //seuraavat yksikkötestit on tehty vähän huonosti ajanpuutteen takia.
    //niitä pitäisi eriyttää lisää, niin että jokainen testi voi mennä pieleen 
    //vain yhdestä syystä.    
    @Test
    public void edullinenKateisostoToimiiTasarahalla() {
        kassapaate.syoEdullisesti(240);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(kassassaRahaa == 100240);
        assertTrue(edullisiaMyyty == 1);
    }
    
    @Test
    public void edullisenKateisostonVaihtorahaOikein() {
        int vaihtoraha = kassapaate.syoEdullisesti(500);
        assertTrue(vaihtoraha == 260);
    }
    
    @Test
    public void edullinenKateisostoEiTapahduKunRahaEiRiita() {
        kassapaate.syoEdullisesti(230);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        int vaihtoraha = kassapaate.syoEdullisesti(230);
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(kassassaRahaa == 100000);
        assertTrue(vaihtoraha == 230);
        assertTrue(edullisiaMyyty == 0);
    }
    
    @Test
    public void maukasKateisostoToimiiTasarahalla() {
        kassapaate.syoMaukkaasti(400);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(kassassaRahaa == 100400);
        assertTrue(maukkaitaMyyty == 1);
    }
    
    @Test
    public void maukkaanKateisostonVaihtorahaOikein() {
        int vaihtoraha = kassapaate.syoMaukkaasti(500);
        assertTrue(vaihtoraha == 100);
    }
   
    @Test
    public void maukasKateisostoEiTapahduKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(300);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        int vaihtoraha = kassapaate.syoMaukkaasti(300);
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(kassassaRahaa == 100000);
        assertTrue(vaihtoraha == 300);
        assertTrue(maukkaitaMyyty == 0);
    }

    @Test
    public void edullinenKorttiostosToimiiKunRahaRiittaa() {
        kortti.lataaRahaa(240);
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        int kortillaRahaa = kortti.saldo();
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(ostosTapahtui);
        assertTrue(kortillaRahaa == 0);  
        assertTrue(edullisiaMyyty == 1);
    }
    
    @Test
    public void edullinenKorttiostosEiTapahduKunRahaEiRiita() {
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        int kortillaRahaa = kortti.saldo();
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertFalse(ostosTapahtui);
        assertTrue(kortillaRahaa == 0);
        assertTrue(edullisiaMyyty == 0);
    }
    
    @Test
    public void maukasKorttiostosToimiiKunRahaRiittaa() {
        kortti.lataaRahaa(400);
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        int kortillaRahaa = kortti.saldo();
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(ostosTapahtui);
        assertTrue(kortillaRahaa == 0);  
        assertTrue(maukkaitaMyyty == 1);
    }
    
    @Test
    public void maukasKorttiostosEiTapahduKunRahaEiRiita() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        int kortillaRahaa = kortti.saldo();
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertFalse(ostosTapahtui);
        assertTrue(kortillaRahaa == 0);
        assertTrue(maukkaitaMyyty == 0);
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuKorttiostossa() {
        kortti.lataaRahaa(240);
        kassapaate.syoEdullisesti(kortti);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100000);
    }
    
    @Test
    public void kunLadataanKortilleRahaaKortinSaldoJaKassaMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti, 500);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        int kortillaRahaa = kortti.saldo();
        assertTrue(kassassaRahaa == 100500);
        assertTrue(kortillaRahaa == 500);
    }
}
