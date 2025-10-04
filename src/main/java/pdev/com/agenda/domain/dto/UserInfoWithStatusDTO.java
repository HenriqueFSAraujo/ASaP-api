package pdev.com.agenda.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoWithStatusDTO {
    private UserInfoDTO userInfo;
    private FormsStatusDTO formsStatus;
}
