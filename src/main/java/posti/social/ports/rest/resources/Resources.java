package posti.social.ports.rest.resources;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;
import static com.theoryinpractise.halbuilder.api.RepresentationFactory.PRETTY_PRINT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(path = Resources.V1)
public class Resources {
    public static final String V1 = "/v1";

    @RequestMapping(method = GET)
    public ResponseEntity<String> entrtypoint() {
        RepresentationFactory representationFactory = new StandardRepresentationFactory().withFlag(PRETTY_PRINT);

        Representation representation = representationFactory.newRepresentation(Resources.V1);
        representation.withLink("user-messages", Resources.V1 + "/users/{userId}/messages");
        representation.withLink("inbox", Resources.V1 + "/users/{userId}/inbox");
        representation.withLink("users", Resources.V1 + "/users");
        representation.withLink("messages", Resources.V1 + "/messages{?authorName,publishedBefore,publishedAfter,contains,page,size}");
        representation.withLink("replies", Resources.V1 + "/messages/{messageId}/replies");
        representation.withLink("followers", Resources.V1 + "/users/{userId}/followers");

       return ok()
             .contentType(MediaType.valueOf(HAL_JSON))
             .body(representation.toString(HAL_JSON));
    }
}
