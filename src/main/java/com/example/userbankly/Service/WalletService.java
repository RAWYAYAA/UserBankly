package com.example.userbankly.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("Wallet")
public interface WalletService {
    @GetMapping("/wallet/{id}")
    public Double getBallance(@PathVariable Long id);

    @PutMapping("/wallet/update")
    public Boolean updateWallet(@RequestParam Long id, @RequestParam Double balance);

}
