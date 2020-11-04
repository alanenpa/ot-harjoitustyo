package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassapaate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        Maksukortti kortti2 = new Maksukortti(10000);
        assertEquals(10000,kortti2.saldo());
    }

    @Test
    public void kortiltaEiVoiOttaaLiikaaRahaa() {
        assertFalse(kortti.otaRahaa(1500));
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals("saldo: 7.60", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals("saldo: 6.0", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosLiianVähänRahaa() {
        Maksukortti kortti2 = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti2);
        assertEquals("saldo: 2.0", kortti2.toString());
    }

    @Test
    public void metodiPalauttaaTrueJosRahaaTarpeeksi() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }

    @Test
    public void metodiPalauttaaFalseJosRahaaEiTarpeeksi() {
        Maksukortti kortti2 = new Maksukortti(200);
        assertFalse(kassapaate.syoMaukkaasti(kortti2));
    }
}
