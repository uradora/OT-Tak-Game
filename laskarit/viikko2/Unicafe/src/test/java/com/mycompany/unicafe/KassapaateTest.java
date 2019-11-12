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
    
    @Test
    public void edullinenKateisostoLisaaKassanRahamaaraaTasarahalla() {
        kassapaate.syoEdullisesti(240);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100240);
    }
    
    @Test
    public void myydytEdullisetLisaantyvatTasarahallaKateisostossa() {
        kassapaate.syoEdullisesti(400);
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(edullisiaMyyty == 1);
    }
    
    @Test
    public void edullisessaKateisostossaKassanMaaraEiMuutuKunRahatEiRiita() {
        kassapaate.syoEdullisesti(230);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100000);
    }
    
    @Test
    public void edullinenKateisostosPalauttaaVaihtorahatKunRahaEiRiita() {
        kassapaate.syoEdullisesti(230);
        int vaihtoraha = kassapaate.syoEdullisesti(230);
        assertTrue(vaihtoraha == 230);
    }    
    
    @Test
    public void myytyjenEdullistenKateisostettujenMaaraEiMuutuKunRahaEiRiita() {
        kassapaate.syoEdullisesti(230);
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(edullisiaMyyty == 0);
    }
    
    @Test
    public void maukasKateisostoLisaaKassanRahamaaraaTasarahalla() {
        kassapaate.syoMaukkaasti(400);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100400);
    }
    
    @Test
    public void myydytMaukkaatLisaantyvatTasarahallaKateisostossa() {
        kassapaate.syoMaukkaasti(400);
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(maukkaitaMyyty == 1);
    }
    
    @Test
    public void maukkaanKateisostonVaihtorahaOikein() {
        int vaihtoraha = kassapaate.syoMaukkaasti(500);
        assertTrue(vaihtoraha == 100);
    }   
    
    @Test
    public void maukkaassaKateisostossaKassanMaaraEiMuutuKunRahatEiRiita() {
        kassapaate.syoMaukkaasti(300);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100000);
    }
    
    @Test
    public void maukasKateisostosPalauttaaVaihtorahatKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(300);
        int vaihtoraha = kassapaate.syoMaukkaasti(300);
        assertTrue(vaihtoraha == 300);
    }    
    
    @Test
    public void kateismyytyjenMaukkaidenMaaraEiMuutuKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(300);
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(maukkaitaMyyty == 0);
    }    
    
    @Test
    public void edullinenKorttiostosTapahtuuKunRahaRiittaa() {
        kortti.lataaRahaa(240);
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        assertTrue(ostosTapahtui);
    } 
    
    @Test
    public void edullinenKorttiostosRahaVaheneeKunRahaRiittaa() {
        kortti.lataaRahaa(240);
        kassapaate.syoEdullisesti(kortti);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 0);  
    }

    @Test
    public void myytyjenEdullistenMaaraLisaantyyKunRahaRiittaa() {
        kortti.lataaRahaa(240);
        kassapaate.syoEdullisesti(kortti);
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(edullisiaMyyty == 1);
    }    
  
    @Test
    public void edullinenKorttiostosEiTapahduKunRahaEiRiita() {
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        assertFalse(ostosTapahtui);
    }
    
    @Test
    public void edullinenKorttiostosKortinSaldoEiMuutuKunRahaEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 0);
    }    
    
    @Test
    public void myytyjenEdullistenMaaraEiMuutuKunRahaEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        assertTrue(edullisiaMyyty == 0);
    }    
    
    @Test
    public void maukasKorttiostosTapahtuuKunRahaRiittaa() {
        kortti.lataaRahaa(400);
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        assertTrue(ostosTapahtui);
    }
    
    @Test
    public void maukasKorttiostosRahaVaheneeKunRahaRiittaa() {
        kortti.lataaRahaa(400);
        kassapaate.syoMaukkaasti(kortti);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 0);  
    }

    @Test
    public void myytyjenMaukkaidenMaaraLisaantyyKunRahaRiittaa() {
        kortti.lataaRahaa(400);
        kassapaate.syoMaukkaasti(kortti);
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(maukkaitaMyyty == 1);
    }    
    
    @Test
    public void maukasKorttiostosEiTapahduKunRahaEiRiita() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        assertFalse(ostosTapahtui);
    }
    
    @Test
    public void maukasKorttiostosKortinSaldoEiMuutuKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 0);
    }    
    
    @Test
    public void myytyjenMaukkaidenMaaraEiMuutuKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        int maukkaitaMyyty = kassapaate.maukkaitaLounaitaMyyty();
        assertTrue(maukkaitaMyyty == 0);
    }    
    
    @Test
    public void kunLadataanKortilleRahaaKortinSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti, 500);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 500);
    }
    
    @Test
    public void kunLadataanKortilleRahaaKassaMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti, 500);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100500);
    }
    
    @Test 
    public void rahaaEiLadataKortilleKunSummaOnNegatiivinen() {
        kassapaate.lataaRahaaKortille(kortti, -10);
        int kortillaRahaa = kortti.saldo();
        assertTrue(kortillaRahaa == 0);
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKunLadattavaSummaOnNegatiivinen() {
        kassapaate.lataaRahaaKortille(kortti, -10);
        int kassassaRahaa = kassapaate.kassassaRahaa();
        assertTrue(kassassaRahaa == 100000);
    }
}
