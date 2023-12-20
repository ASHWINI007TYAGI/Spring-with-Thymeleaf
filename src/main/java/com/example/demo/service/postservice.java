package com.example.demo.service;



import com.example.demo.payload.postdto;

import java.util.List;

public interface postservice {
    postdto createpost(postdto postdto);
    List<postdto> getallpost();
    postdto getpostbyid(long id);
    postdto updatepost(postdto postdto,long id);
    void deletepost(long id);
}
