package org.example.qaTransactionTeam.backEnd.utils;

public class Configs {

    //DB_configs
    public static final String ITMTST_ALL_NAME = "asgate";
    public static final String ITMTST_ALL_PASSWORD = "Continent";

    public static final String POSTGRE_SQL_VMTDB_NAME = "vmt";
    public static final String POSTGRE_SQL_VMTDB_PASSWORD = "passw0rd";

    public static final String POSTGRE_SQL_KREEDB_NAME = "svc_kree";
    public static final String POSTGRE_SQL_KREEDB_PASSWORD = "passw0rd";

    public static final String POSTGRE_SQL_BEYONDER_NAME = "beyonder";
    public static final String POSTGRE_SQL_BEYONDER_PASSWORD = "passw0rd";

    public static final String MONGO_DB_HOST = "dc3-gmdb-001-vm.dev-fuib.com";
    public static final int MONGO_DB_PORT = 37017;
    public static final char [] MONGO_DB_PASSWORD = {'p','a','s','s','w','0','r','d'};

    public static final String HCE_ROLEID = "7a0adf69-c8e6-223e-a22f-e5162cd7167f";
    public static final String HCE_SECRETID = "c58a79ff-f338-e858-767d-88c7835e3f5d";
    public static final String HCE_TOKEN_REQUESTOR_ID = "400100750";
    public static final String HCE_TOKEN_REFERENCE_ID = "1";
    public static final String HCE_APIKEY = "";
    public static final String HCE_JWS = "eyJraWQiOiJYQVAxSEhPVlJON0YyNFk5N1VDODEzZHNIeUhqUlVRZC1FWWxqVlR6QnUxZnQ0akVRIiwiY3R5IjoiSldFIiwiYWxnIjoiUFMyNTYifQ.ZXlKMGVYQWlPaUpLVDFORklpd2laVzVqSWpvaVFUSTFOa2REVFNJc0ltbGhkQ0k2TVRZeE5UZzVNekl3T1RFM055d2lZV3huSWpvaVVsTkJMVTlCUlZBdE1qVTJJaXdpYTJsa0lqb2lWRUZVVmxNeFZ6aFpWRVJMU1ZNNU1WaE1Xall4TTBKalNVbHdNRTF2YkdKQlMydFdXWFZLVEhSWU1XTlVNelIzVVNKOS5LaER4X2hZQ0YtZkZfRThNUW5HV1BhOTBkVnVhSkhNbjYwV2p6T2tBZThmcUhTWlBfbFBNemlLYjBJWWdaRXJwV0lUTlNCM3ZFWTNTS1ZVenVSMTZ4NnRnUWdWT0JMRllCZXNoenJGdW45by1SUHlxTS13eFliMFFtWXZaeWVseDFKWXVSa0x0ZlU4Z2hhdFhmaXAyY2Y0T0lEYVg5eFNHcWRwVFhEZDBaV2g2Y1lPNkRzV0dydXNRekxYbTZTbUdVUFpjTjRELUhTcjRIMHRRYjlCV1czNldDMjUyMEFNajNkNElyNEFOalBsVDB0Y1hWcExXWk50RF9CdVdsNTVDWWYxOF80N3J1aFV5dzlTSHJWVTI2cTlfU0p0REZnd090dDNyem91RmJNUUp5R0hvOTBtWEtrY3Ryb3V6bWIxWmR0ZXBscy1FS3hBeG1RZWxlYkllaHcuSzVqOVNiS3pTR2VaR1lzbi5MUFVuSWY2LVU1WmVQUDU2b1VwY2RBSXN3ei1SYkZaVmRuU2E2TWM4S1NNeXRwQm9wWnNJelRhMHBZOGVHRzJQSi0zb1lvRzM4STRuYTRmMGtKY0duOW5NdHJSYTF1M3FFSUhpSG5ld2YwWmdaUEk3QWU0X2JNa0N4MW5INWdVR1Z5d3VhZHJGMXJWRmt0SHBuYl9tYUgtbU9wczRnNXBoc3NEWTUxX3BQa3c5ODdDZmJYX19PVFowTzRJZy1RN0ZzZ3VZQk9tME12NzBKZW15d0VkN0FITTJKNlhDc1hYM1p3ZVdOUEhCcVZEaWs5cWg0YzFyY1NSN01hUjAwWlJLY2tQbTZXRFlMRzM4TTBKa0VjdFh1dmotNGs4SjZ3bUEwWm9aaDRZOVhtaGJkeWlPa1FDdEY1aFFLTHFTdS1WWldLM29mUGVMdGV3QWRIamtzTG5BOG1aQVNKXzhVTEZtRE80QUJoeUY3VklfN1NfR0FpUW5iaFh5M3dHM1JDYzZXX0p2OHlwVjF2a215U3VucHZGZjZsWHVqUTAyTE1rWXBQVzMtYVRxSHpwbmVGT21ud2ZRRk9ZNThiZHpvc2lOdzJUYVpad0ptZUtJb1F2SXRCWF8zcURFb0J2Qk1KdmNHOUNpQ0FrU3U1VkJ0Q0VTSjVIVThMcmFYUW1ZQ3lrN0VLMzJkWlN0VWlEUGRtNkM3TmZqUGJ2N0RxMDM4RkRNeEdqNEt6cnNZbXFsbGc2QkFnQzNOdGllRzZZTnlBaTkwNW9FWFRLQTlLQXdfWDI4b3BRd1JMREMzU2VTamZfRWRDMjkyVHp6OWtkbXE5YXJIcV83ek5wU0xRcUdaZGJpT1l5U0s3cjFIU01nTmxMYU5HUXpYZWRwRVVfZ0M5OEtFZWxZakVjbFZ6UlFady1VMnplQXZ2dlJXTTlwV1R4NDR2dGdfazhhM1hnM0xYRHk3d0gwLVFQSEI2OUlBRmc1RXlsQWROS3pobmFEWWVPQWQteFI0bWlSU3U5Z1o2bENxdUt6RkMwNnZTQm5Jbm8wdU1zdGE1OHN6TW9VcTZPZHhzOExVX3MxTHdCczBPMEgyVjAwZXJMRFpMaGtqSUl3Ym1rUlpHS0JNSjhYdU5KY3EyUWktWHRPN1c0bDFWQi1OX3pqcGNlM21WbnZPTHNsWXlselpXcl9pVGxJa2JLQ0VvS204c04xWlQ2RGxSQS4ycXNZU0Q5Y0xCTG1PMVdVaDZPOTln.QOA1xTdYRPPIOEd16IxXKy3sCjunt1YuqSW1CwkVOMWx3Me5dm4ONF4YQwVyvNy5Vq0nOXzR0ed6xnEp6XlV405K6a-vMVPEeYiI8WXLkyc5kmZ6jlupzhZrNlmHObWqF2rqZrLDFpdk5Y4ZkpaZWIRQIv1kqP5xgEkiLiqKZdhEYZLWOE2ZpSL5-opQzjhBtba1LjYvV2XpGLbqaD3mv-0KvxzD74umnJE03og4yczI66Z-Mu5dGJlcf20epO0uKwDcaAivor1k89ilEQoxtWfh4NoTIdnDJOkV75LxftRUupXRbHSWLzEgCNH4IOmtw2K6k5OI07-XGU7rCC9RUEodhdPcv1dcheJQU8_Yikd5jn50w4qq_twImzjqNrMeEZTC9NYG9IshSZcEQFqbSn8hbkpM8iwt7TnugOcI_ArRvkEo4utl5P0Q6PvBo4CL8T_8vRJLgjZdlesxfvJwaoOel0VDcoXkV-1Px4QEJCNvdpAhwHqA1MonQ7N9n2ZWpU8gvYSZ5iSpWCO6hCDTZDiM0il5gaZriPGkgU-i11CpgsaA75PNj5hjS7cfiIXpjT0YWq1wzXXqPzb8MoKGnhC4S0N1voxQ0NMdYOd6Sqk0Jd9eYHlH5XnrZueCpHQdziYmDpRn7E-Mloq9EdSs9gHI8bWNu6lsqpoDFS0y0dU";

    public static final String RONAN_LOGIN_2101 = "TSTUSRC2C";
    public static final String RONAN_PASSWORD_2101 = "4V#tHIl5oEWQ]?9p3INKE(&f1&pf+/s*XdF6G45Y3$[QcbSRKl7972Vu07@>";

    //public static final String ACCESS_TO_RABBIT = "amqp://guest:guest@dc3-rmq-001-vs.dev-fuib.com:35672/";
    public static final String ACCESS_TO_RABBIT_phd_rb_003 = "amqp://admin:admin@dc3-phd-rb-003-vs.dev-fuib.com:5672/";
    public static final String ACCESS_TO_RABBIT = "amqp://tsys:tsys@dc3-core-rmq-002-vs.stage2-fuib.com:35672";

    public static final String TaxCode = "3462406450";

    public static final String LOGIN_MERCHANT_2101 = "TSTUSRC2C";
    public static final String PASSWORD_MERCHANT_2101 = "Mm0>E@K6q?9uk>_v08)v<Zszc@]4]BpY<_297(365El2a4_n3Z?2YI+r4.Ba";

    public static final String PAYHUB_HOST = "https://innsmouth.test-fuib.com"; // Test - https://innsmouth.payhub.com.ua  https://innsmouth.test-fuib.com; DEV - https://innsmouth.dev-fuib.com  PROD - https://rlyeh.payhub.com.ua  STAGE - https://innsmouth.stage-fuib.com
    public static final String PAYHUB_LOGIN = "svc_ph_test_trn";                     //svc_ph_test_ptrn                  svc_ph_test_trn                    //svc_tpuo_ph
    public static final String PAYHUB_PASSWORD = "EhK#E47R6S255BAneS2e3UBh6&viLtrZ"; //quxS2&56xvatPZz66LKG8sJQHn3ZYVSA  EhK#E47R6S255BAneS2e3UBh6&viLtrZ ApBMQ7zx4F3K3TXyQkyXRHPp37CFtq
    public static final String PAYHUB_CLIENT = "transacter";

    public static final String ADMIN_LOGIN = "svc_ph_test_trn";
    public static final String ADMIN_PASSWORD = "EhK#E47R6S255BAneS2e3UBh6&viLtrZ";


}