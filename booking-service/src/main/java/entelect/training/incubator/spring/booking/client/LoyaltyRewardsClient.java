package entelect.training.incubator.spring.booking.client;

import entelect.training.incubator.spring.booking.client.soap.CaptureRewardsRequest;
import entelect.training.incubator.spring.booking.client.soap.CaptureRewardsResponse;
import entelect.training.incubator.spring.booking.client.soap.RewardsBalanceRequest;
import entelect.training.incubator.spring.booking.client.soap.RewardsBalanceResponse;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigDecimal;

@Service
public class LoyaltyRewardsClient extends WebServiceGatewaySupport {
    private static final BigDecimal REWARD_AMOUNT = BigDecimal.valueOf(100.00);

    public RewardsBalanceResponse getRewardBalance(String passport) {
        RewardsBalanceRequest request = new RewardsBalanceRequest();
        request.setPassportNumber(passport);

        RewardsBalanceResponse response = (RewardsBalanceResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response;
    }

    public CaptureRewardsResponse captureReward(String passport){
        CaptureRewardsRequest request = new CaptureRewardsRequest();
        request.setPassportNumber(passport);
        request.setAmount(REWARD_AMOUNT);

        CaptureRewardsResponse response = (CaptureRewardsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response;
    }
}