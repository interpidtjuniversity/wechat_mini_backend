package com.miniapp.wechat.wechat_mini_backend.convetor;

import com.miniapp.wechat.wechat_mini_backend.dto.LoginDO;
import com.miniapp.wechat.wechat_mini_backend.dto.VerifyCodeDO;
import com.miniapp.wechat.wechat_mini_backend.frontend.LoginDTO;
import com.miniapp.wechat.wechat_mini_backend.frontend.VerifyCodeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FrontDTOConvertor {

    FrontDTOConvertor INSTANCE = Mappers.getMapper(FrontDTOConvertor.class);

    VerifyCodeDTO verifyCodeDO2DTO(VerifyCodeDO verifyCodeDO);

    LoginDO loginDTO2DO(LoginDTO loginDTO);

    VerifyCodeDO verifyCodeDTO2DO(VerifyCodeDTO verifyCodeDTO);

    LoginDTO loginDO2DTO(LoginDO loginDO);
}
