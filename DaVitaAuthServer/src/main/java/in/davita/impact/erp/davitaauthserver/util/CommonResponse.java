package in.davita.impact.erp.davitaauthserver.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    private boolean isValidRequest;
    private Object responseData;
    private Integer status;
   
    
    
}