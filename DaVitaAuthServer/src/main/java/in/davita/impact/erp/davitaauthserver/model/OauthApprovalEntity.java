package in.davita.impact.erp.davitaauthserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Prashant Wankhede on 30-04-2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_approvals")
public class OauthApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String clientId;
    private String scope;
    private String status;
    private Date expiresAt;
    private Date lastModifiedAt;
}