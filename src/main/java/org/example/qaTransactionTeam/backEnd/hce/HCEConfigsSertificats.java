package org.example.qaTransactionTeam.backEnd.hce;

import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import org.example.qaTransactionTeam.backEnd.utils.Configs;

public class HCEConfigsSertificats {
    //https://confluence.fuib.com/display/SD/Environment

    //https://vault.dev-fuib.com/ui/vault/secrets/hce/show/vts/visa
    //https://vault.dev-fuib.com/ui/vault/secrets/hce/show/vts/inbound

    public static String findVault(String path, String key) throws VaultException {

        final var vaultConfig =
                new VaultConfig()
                        .address("https://vault.dev-fuib.com/")
                        .engineVersion(1)
                        .openTimeout(5)
                        .readTimeout(30)
                        .sslConfig(new SslConfig().verify(false).build())
                        .build();
        var vault = new Vault(vaultConfig);
        final var auth =
                vault
                        .auth()
                        .loginByAppRole(Configs.HCE_ROLEID, Configs.HCE_SECRETID);
        vaultConfig.token(auth.getAuthClientToken());
        vault = new Vault(vaultConfig);
        final var read = vault.logical().read("hce/vts/"+path+"");
        String value = read.getData().get(key);
        return value;

    }
}