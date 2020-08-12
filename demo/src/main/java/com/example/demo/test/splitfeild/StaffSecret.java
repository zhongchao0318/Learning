package com.example.demo.test.splitfeild;

import lombok.Data;

import java.io.Serializable;


/**
 * 员工加密后数据
 */
@Data
public class StaffSecret implements Serializable {
    private String pay_prd_id;              /* 年/月 例子：2020/05 */

    private String setid;

    private String deptid;                  /*  部门id    */

    private String emplid;                  /*  员工编号    */

    private String name;                    /*  员工姓名    */

    private String account_ec_id;           /*  银行卡号    */

    private String hire_dt;                 /*  雇佣日期    */

    private String cg_fix_sal;

    private String cg_base_sal;

    private String cg_posn_sal;

    private String cg_tlvl_sal;

    private String cg_oth_sal;

    private String cg_alow_coll_sal;

    private String cg_ngsf_alow_sal;

    private String cg_ltng_alow_sal;

    private String cg_posn_alow_sal;

    private String cg_frth_alow_sal;

    private String cg_cash_alow_sal;

    private String cg_eplf_alow_sal;

    private String cg_epac_alow_sal;

    private String cg_nwst_alow_sal;

    private String cg_prob_alow_sal;

    private String cg_agen_alow_sal;

    private String cg_conc_alow_sal;

    private String cg_hgtp_alow_sal;

    private String cg_fklt_alow_sal;

    private String cg_dbhd_alow_sal;

    private String cg_meal_alow_sal;

    private String cg_frsb_alow_sal;

    private String cg_lwtp_alow_sal;

    private String cg_supt_alow_sal;

    private String cg_slbs_alow_sal;

    private String cg_dppb_alow_sal;

    private String cg_pras_alow_sal;

    private String cg_spop_alow_sal;

    private String cg_inno_alow_sal;

    private String cg_fglg_alow_sal;

    private String cg_oth1_alow_sal;

    private String cg_oth2_alow_sal;

    private String cg_oth3_alow_sal;

    private String cg_oth4_alow_sal;

    private String cg_oth5_alow_sal;

    private String cg_oth6_alow_sal;

    private String cg_bons_coll_sal;

    private String cg_cash_bons_sal;

    private String cg_slpt_bons_sal;

    private String cg_clpf_bons_sal;

    private String cg_gppu_bons_sal;

    private String cg_flot_bons_sal;

    private String cg_ivpr_bons_sal;

    private String cg_qtpf_bons_sal;

    private String cg_flat_bons_sal;

    private String cg_cscp_bons_sal;

    private String cg_spsh_bons_sal;

    private String cg_oth1_bons_sal;

    private String cg_oth2_bons_sal;

    private String cg_oth3_bons_sal;

    private String cg_psf1_bons_sal;

    private String cg_psf2_bons_sal;

    private String cg_psf3_bons_sal;

    private String cg_pac1_bons_sal;

    private String cg_pac2_bons_sal;

    private String cg_pac3_bons_sal;

    private String cg_cumn_ovtm_sal;

    private String cg_wkdy_ovtm_sal;

    private String cg_wknd_ovtm_sal;

    private String cg_hoil_ovtm_sal;

    private String cg_lave_detc_sal;

    private String cg_aslv_detc_sal;

    private String cg_sklv_detc_sal;

    private String cg_anlv_detc_sal;

    private String cg_ngwk_detc_sal;

    private String cg_accd_incm_sal;

    private String cg_serv_incm_sal;

    private String cg_ohdi_coll_sal;

    private String cg_ecnc_comp_sal;

    private String cg_medl_comp_sal;

    private String cg_othe_supl_sal;

    private String cg_othe_detc_sal;

    private String cg_slir_per_sal;

    private String cg_pacc_per_sal;

    private String cg_annl_lump_sal;

    private String cg_thir_pay_sal;

    private String cg_annl_bons_sal;

    private String cg_bftx_sum_tax_sal;

    private String cg_pert_sum_tax_sal;

    private String cg_pers_incm_tax_sal;

    private String cg_accd_incm_tax_sal;

    private String cg_serv_tax_sal;

    private String cg_annl_bons_tax_sal;

    private String cg_aftx_indc_sal;

    private String cg_ecnc_comp_ntx_sal;

    private String cg_aftx_incm_sal;

    private String cg_aftx_detc_sal;

    private String cg_trad_sal;

    private String cg_tai_ai_fund_sal;

    private String cg_net_pay_sal;

    private String encoded;                     /*加密标识 T未加密 S已加密*/

    private String cg_tm_actl_hour;
}
