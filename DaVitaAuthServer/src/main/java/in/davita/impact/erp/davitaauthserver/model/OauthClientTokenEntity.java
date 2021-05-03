package in.davita.impact.erp.davitaauthserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Prashant Wankhede on 30-04-2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_token")
public class OauthClientTokenEntity {

    @Column(name = "token_id")
    private String tokenId;
//    @Lob
    private byte[] token;
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "client_id")
    private String clientId;

}