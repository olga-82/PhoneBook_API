package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class ContactDTO {
//    id	string
//    name*	string
//    lastName*	string
//    email	string
//    phone	string
//    pattern: ^\d{10,15}$
//    address*	string
//    description	string

   String id ;
   String name ;
   String lastName ;
   String email ;
   String phone ;
   String address ;
   String description ;


}

