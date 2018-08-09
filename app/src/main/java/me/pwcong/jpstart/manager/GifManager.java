package me.pwcong.jpstart.manager;

import java.util.concurrent.ConcurrentHashMap;

import me.pwcong.jpstart.R;
import me.pwcong.jpstart.mvp.bean.JPGif;

/**
 * Created by Pwcong on 2016/10/11.
 */

public class GifManager {

    private static GifManager instance;

    private ConcurrentHashMap<String, JPGif> gifs = new ConcurrentHashMap<>();

    private GifManager() {
    }

    public static synchronized GifManager getInstance() {
        if (instance == null) {
            instance = new GifManager();
        }

        return instance;

    }

    public void init() {
        gifs.put("a", new JPGif("a", R.raw.gif_a, R.raw.gif_a_));
        gifs.put("ba", new JPGif("ba", R.raw.gif_ba, R.raw.gif_ba_));
        gifs.put("be", new JPGif("be", R.raw.gif_be, R.raw.gif_be_));
        gifs.put("bi", new JPGif("bi", R.raw.gif_bi, R.raw.gif_bi_));
        gifs.put("bo", new JPGif("bo", R.raw.gif_bo, R.raw.gif_bo_));
        gifs.put("bu", new JPGif("bu", R.raw.gif_bu, R.raw.gif_bu_));
        gifs.put("chi", new JPGif("chi", R.raw.gif_chi, R.raw.gif_chi_));
        gifs.put("da", new JPGif("da", R.raw.gif_da, R.raw.gif_da_));
        gifs.put("de", new JPGif("de", R.raw.gif_de, R.raw.gif_de_));
        gifs.put("do", new JPGif("do", R.raw.gif_do, R.raw.gif_do_));
        gifs.put("du", new JPGif("du", R.raw.gif_du, R.raw.gif_du_));
        gifs.put("e", new JPGif("e", R.raw.gif_e, R.raw.gif_e_));
        gifs.put("fu", new JPGif("fu", R.raw.gif_fu, R.raw.gif_fu_));
        gifs.put("ga", new JPGif("ga", R.raw.gif_ga, R.raw.gif_ga_));
        gifs.put("ge", new JPGif("ge", R.raw.gif_ge, R.raw.gif_ge_));
        gifs.put("gi", new JPGif("gi", R.raw.gif_gi, R.raw.gif_gi_));
        gifs.put("go", new JPGif("go", R.raw.gif_go, R.raw.gif_go_));
        gifs.put("gu", new JPGif("gu", R.raw.gif_gu, R.raw.gif_gu_));
        gifs.put("ha", new JPGif("ha", R.raw.gif_ha, R.raw.gif_ha_));
        gifs.put("he", new JPGif("he", R.raw.gif_he, R.raw.gif_he_));
        gifs.put("hi", new JPGif("hi", R.raw.gif_hi, R.raw.gif_hi_));
        gifs.put("ho", new JPGif("ho", R.raw.gif_ho, R.raw.gif_ho_));
        gifs.put("i", new JPGif("i", R.raw.gif_i, R.raw.gif_i_));
        gifs.put("ji", new JPGif("ji", R.raw.gif_ji, R.raw.gif_ji_));
        gifs.put("ka", new JPGif("ka", R.raw.gif_ka, R.raw.gif_ka_));
        gifs.put("ke", new JPGif("ke", R.raw.gif_ke, R.raw.gif_ke_));
        gifs.put("ki", new JPGif("ki", R.raw.gif_ki, R.raw.gif_ki_));
        gifs.put("ko", new JPGif("ko", R.raw.gif_ko, R.raw.gif_ko_));
        gifs.put("ku", new JPGif("ku", R.raw.gif_ku, R.raw.gif_ku_));
        gifs.put("ma", new JPGif("ma", R.raw.gif_ma, R.raw.gif_ma_));
        gifs.put("me", new JPGif("me", R.raw.gif_me, R.raw.gif_me_));
        gifs.put("mi", new JPGif("mi", R.raw.gif_mi, R.raw.gif_mi_));
        gifs.put("mo", new JPGif("mo", R.raw.gif_mo, R.raw.gif_mo_));
        gifs.put("mu", new JPGif("mu", R.raw.gif_mu, R.raw.gif_mu_));
        gifs.put("n", new JPGif("n", R.raw.gif_n, R.raw.gif_n_));
        gifs.put("na", new JPGif("na", R.raw.gif_na, R.raw.gif_na_));
        gifs.put("ne", new JPGif("ne", R.raw.gif_ne, R.raw.gif_ne_));
        gifs.put("ni", new JPGif("ni", R.raw.gif_ni, R.raw.gif_ni_));
        gifs.put("no", new JPGif("no", R.raw.gif_no, R.raw.gif_no_));
        gifs.put("nu", new JPGif("nu", R.raw.gif_nu, R.raw.gif_nu_));
        gifs.put("o", new JPGif("o", R.raw.gif_o, R.raw.gif_o_));
        gifs.put("pa", new JPGif("pa", R.raw.gif_pa, R.raw.gif_pa_));
        gifs.put("pe", new JPGif("pe", R.raw.gif_pe, R.raw.gif_pe_));
        gifs.put("pi", new JPGif("pi", R.raw.gif_pi, R.raw.gif_pi_));
        gifs.put("po", new JPGif("po", R.raw.gif_po, R.raw.gif_po_));
        gifs.put("pu", new JPGif("pu", R.raw.gif_pu, R.raw.gif_pu_));
        gifs.put("ra", new JPGif("ra", R.raw.gif_ra, R.raw.gif_ra_));
        gifs.put("re", new JPGif("re", R.raw.gif_re, R.raw.gif_re_));
        gifs.put("ri", new JPGif("ri", R.raw.gif_ri, R.raw.gif_ri_));
        gifs.put("ro", new JPGif("ro", R.raw.gif_ro, R.raw.gif_ro_));
        gifs.put("ru", new JPGif("ru", R.raw.gif_ru, R.raw.gif_ru_));
        gifs.put("sa", new JPGif("sa", R.raw.gif_sa, R.raw.gif_sa_));
        gifs.put("se", new JPGif("se", R.raw.gif_se, R.raw.gif_se_));
        gifs.put("shi", new JPGif("shi", R.raw.gif_shi, R.raw.gif_shi_));
        gifs.put("so", new JPGif("so", R.raw.gif_so, R.raw.gif_so_));
        gifs.put("su", new JPGif("su", R.raw.gif_su, R.raw.gif_su_));
        gifs.put("ta", new JPGif("ta", R.raw.gif_ta, R.raw.gif_ta_));
        gifs.put("te", new JPGif("te", R.raw.gif_te, R.raw.gif_te_));
        gifs.put("to", new JPGif("to", R.raw.gif_to, R.raw.gif_to_));
        gifs.put("tsu", new JPGif("tsu", R.raw.gif_tsu, R.raw.gif_tsu_));
        gifs.put("u", new JPGif("u", R.raw.gif_u, R.raw.gif_u_));
        gifs.put("wa", new JPGif("wa", R.raw.gif_wa, R.raw.gif_wa_));
        gifs.put("wo", new JPGif("wo", R.raw.gif_wo, R.raw.gif_wo_));
        gifs.put("ya", new JPGif("ya", R.raw.gif_ya, R.raw.gif_ya_));
        gifs.put("yo", new JPGif("yo", R.raw.gif_yo, R.raw.gif_yo_));
        gifs.put("yu", new JPGif("yu", R.raw.gif_yu, R.raw.gif_yu_));
        gifs.put("za", new JPGif("za", R.raw.gif_za, R.raw.gif_za_));
        gifs.put("ze", new JPGif("ze", R.raw.gif_ze, R.raw.gif_ze_));
        gifs.put("zi", new JPGif("zi", R.raw.gif_zi, R.raw.gif_zi_));
        gifs.put("zo", new JPGif("zo", R.raw.gif_zo, R.raw.gif_zo_));
        gifs.put("zu", new JPGif("zu", R.raw.gif_zu, R.raw.gif_zu_));

    }

    public JPGif getJPGif(String rome) {

        return gifs.get(rome);

    }


}
