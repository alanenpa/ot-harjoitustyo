package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Maksukortti kortti;
    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassapaate = new Kassapaate();
    }

    @Test
    public void maaratAlussaOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateisostoMaukasToimii() {
        assertEquals(100,kassapaate.syoMaukkaasti(500));
        assertEquals(100400,kassapaate.kassassaRahaa());
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateisostoEdullinenToimii() {
        assertEquals(260,kassapaate.syoEdullisesti(500));
        assertEquals(100240,kassapaate.kassassaRahaa());
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kateismaksuEiRiittavaMaukkaaseen() {
        assertEquals(399, kassapaate.syoMaukkaasti(399));
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateismaksuEiRiittavaEdulliseen() {
        assertEquals(239, kassapaate.syoEdullisesti(239));
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttimaksuMaukasToimii() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600,kortti.saldo());
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttimaksuEdullinenToimii() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760,kortti.saldo());
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(2,kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttimaksuEiRiittavaMaukkaaseen() {
        Maksukortti kortti2 = new Maksukortti(399);
        assertFalse(kassapaate.syoMaukkaasti(kortti2));
        assertEquals(399, kortti2.saldo());
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttimaksuEiRiittavaEdulliseen() {
        Maksukortti kortti2 = new Maksukortti(239);
        assertFalse(kassapaate.syoEdullisesti(kortti2));
        assertEquals(239, kortti2.saldo());
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassaEiMuutuKortillaOstaessa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortilleLatausToimii() {
        kassapaate.lataaRahaaKortille(kortti, 700);
        assertEquals(1700, kortti.saldo());
        assertEquals(100700, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortilleLatausEiToimiNegatiivisellaSummalla() {
        kassapaate.lataaRahaaKortille(kortti, -200);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}