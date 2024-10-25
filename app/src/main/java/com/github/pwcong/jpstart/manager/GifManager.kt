package com.github.pwcong.jpstart.manager

import java.util.concurrent.ConcurrentHashMap

import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.mvp.bean.JPGif

class GifManager private constructor() {
    private val gifs = ConcurrentHashMap<String, JPGif>()

    fun init() {
        gifs["a"] = JPGif("a", R.raw.gif_a, R.raw.gif_a_)
        gifs["ba"] = JPGif("ba", R.raw.gif_ba, R.raw.gif_ba_)
        gifs["be"] = JPGif("be", R.raw.gif_be, R.raw.gif_be_)
        gifs["bi"] = JPGif("bi", R.raw.gif_bi, R.raw.gif_bi_)
        gifs["bo"] = JPGif("bo", R.raw.gif_bo, R.raw.gif_bo_)
        gifs["bu"] = JPGif("bu", R.raw.gif_bu, R.raw.gif_bu_)
        gifs["chi"] = JPGif("chi", R.raw.gif_chi, R.raw.gif_chi_)
        gifs["da"] = JPGif("da", R.raw.gif_da, R.raw.gif_da_)
        gifs["de"] = JPGif("de", R.raw.gif_de, R.raw.gif_de_)
        gifs["do"] = JPGif("do", R.raw.gif_do, R.raw.gif_do_)
        gifs["du"] = JPGif("du", R.raw.gif_du, R.raw.gif_du_)
        gifs["e"] = JPGif("e", R.raw.gif_e, R.raw.gif_e_)
        gifs["fu"] = JPGif("fu", R.raw.gif_fu, R.raw.gif_fu_)
        gifs["ga"] = JPGif("ga", R.raw.gif_ga, R.raw.gif_ga_)
        gifs["ge"] = JPGif("ge", R.raw.gif_ge, R.raw.gif_ge_)
        gifs["gi"] = JPGif("gi", R.raw.gif_gi, R.raw.gif_gi_)
        gifs["go"] = JPGif("go", R.raw.gif_go, R.raw.gif_go_)
        gifs["gu"] = JPGif("gu", R.raw.gif_gu, R.raw.gif_gu_)
        gifs["ha"] = JPGif("ha", R.raw.gif_ha, R.raw.gif_ha_)
        gifs["he"] = JPGif("he", R.raw.gif_he, R.raw.gif_he_)
        gifs["hi"] = JPGif("hi", R.raw.gif_hi, R.raw.gif_hi_)
        gifs["ho"] = JPGif("ho", R.raw.gif_ho, R.raw.gif_ho_)
        gifs["i"] = JPGif("i", R.raw.gif_i, R.raw.gif_i_)
        gifs["ji"] = JPGif("ji", R.raw.gif_ji, R.raw.gif_ji_)
        gifs["ka"] = JPGif("ka", R.raw.gif_ka, R.raw.gif_ka_)
        gifs["ke"] = JPGif("ke", R.raw.gif_ke, R.raw.gif_ke_)
        gifs["ki"] = JPGif("ki", R.raw.gif_ki, R.raw.gif_ki_)
        gifs["ko"] = JPGif("ko", R.raw.gif_ko, R.raw.gif_ko_)
        gifs["ku"] = JPGif("ku", R.raw.gif_ku, R.raw.gif_ku_)
        gifs["ma"] = JPGif("ma", R.raw.gif_ma, R.raw.gif_ma_)
        gifs["me"] = JPGif("me", R.raw.gif_me, R.raw.gif_me_)
        gifs["mi"] = JPGif("mi", R.raw.gif_mi, R.raw.gif_mi_)
        gifs["mo"] = JPGif("mo", R.raw.gif_mo, R.raw.gif_mo_)
        gifs["mu"] = JPGif("mu", R.raw.gif_mu, R.raw.gif_mu_)
        gifs["n"] = JPGif("n", R.raw.gif_n, R.raw.gif_n_)
        gifs["na"] = JPGif("na", R.raw.gif_na, R.raw.gif_na_)
        gifs["ne"] = JPGif("ne", R.raw.gif_ne, R.raw.gif_ne_)
        gifs["ni"] = JPGif("ni", R.raw.gif_ni, R.raw.gif_ni_)
        gifs["no"] = JPGif("no", R.raw.gif_no, R.raw.gif_no_)
        gifs["nu"] = JPGif("nu", R.raw.gif_nu, R.raw.gif_nu_)
        gifs["o"] = JPGif("o", R.raw.gif_o, R.raw.gif_o_)
        gifs["pa"] = JPGif("pa", R.raw.gif_pa, R.raw.gif_pa_)
        gifs["pe"] = JPGif("pe", R.raw.gif_pe, R.raw.gif_pe_)
        gifs["pi"] = JPGif("pi", R.raw.gif_pi, R.raw.gif_pi_)
        gifs["po"] = JPGif("po", R.raw.gif_po, R.raw.gif_po_)
        gifs["pu"] = JPGif("pu", R.raw.gif_pu, R.raw.gif_pu_)
        gifs["ra"] = JPGif("ra", R.raw.gif_ra, R.raw.gif_ra_)
        gifs["re"] = JPGif("re", R.raw.gif_re, R.raw.gif_re_)
        gifs["ri"] = JPGif("ri", R.raw.gif_ri, R.raw.gif_ri_)
        gifs["ro"] = JPGif("ro", R.raw.gif_ro, R.raw.gif_ro_)
        gifs["ru"] = JPGif("ru", R.raw.gif_ru, R.raw.gif_ru_)
        gifs["sa"] = JPGif("sa", R.raw.gif_sa, R.raw.gif_sa_)
        gifs["se"] = JPGif("se", R.raw.gif_se, R.raw.gif_se_)
        gifs["shi"] = JPGif("shi", R.raw.gif_shi, R.raw.gif_shi_)
        gifs["so"] = JPGif("so", R.raw.gif_so, R.raw.gif_so_)
        gifs["su"] = JPGif("su", R.raw.gif_su, R.raw.gif_su_)
        gifs["ta"] = JPGif("ta", R.raw.gif_ta, R.raw.gif_ta_)
        gifs["te"] = JPGif("te", R.raw.gif_te, R.raw.gif_te_)
        gifs["to"] = JPGif("to", R.raw.gif_to, R.raw.gif_to_)
        gifs["tsu"] = JPGif("tsu", R.raw.gif_tsu, R.raw.gif_tsu_)
        gifs["u"] = JPGif("u", R.raw.gif_u, R.raw.gif_u_)
        gifs["wa"] = JPGif("wa", R.raw.gif_wa, R.raw.gif_wa_)
        gifs["wo"] = JPGif("wo", R.raw.gif_wo, R.raw.gif_wo_)
        gifs["ya"] = JPGif("ya", R.raw.gif_ya, R.raw.gif_ya_)
        gifs["yo"] = JPGif("yo", R.raw.gif_yo, R.raw.gif_yo_)
        gifs["yu"] = JPGif("yu", R.raw.gif_yu, R.raw.gif_yu_)
        gifs["za"] = JPGif("za", R.raw.gif_za, R.raw.gif_za_)
        gifs["ze"] = JPGif("ze", R.raw.gif_ze, R.raw.gif_ze_)
        gifs["zi"] = JPGif("zi", R.raw.gif_zi, R.raw.gif_zi_)
        gifs["zo"] = JPGif("zo", R.raw.gif_zo, R.raw.gif_zo_)
        gifs["zu"] = JPGif("zu", R.raw.gif_zu, R.raw.gif_zu_)
    }

    fun getJPGif(rome: String): JPGif? {
        return gifs[rome]
    }

    companion object {
        private var instance: GifManager? = null

        @Synchronized
        fun getInstance(): GifManager {
            if (instance == null) {
                instance = GifManager()
            }

            return instance!!
        }
    }
}