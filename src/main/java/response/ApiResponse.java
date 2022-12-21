package response;


import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

@Getter
@Setter
public class ApiResponse extends BaseResponse {

    private int code;
    private String type;
    private String message;

    public void assertErrorData(String type,int code, String message) {
        Assert.assertEquals(getType(), type);
        Assert.assertEquals(getCode(), code);
        Assert.assertEquals(getMessage(), message);
    }

    public void assertSuccessData(String type) {
        Assert.assertEquals(getCode(), 200);
        Assert.assertEquals(getMessage(), "ok");
        Assert.assertNotEquals(getType(), "error");
        Assert.assertEquals(getType(), type);
    }

}
