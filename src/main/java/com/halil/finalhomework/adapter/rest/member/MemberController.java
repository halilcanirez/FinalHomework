package com.halil.finalhomework.adapter.rest.member;


import com.halil.finalhomework.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    @ResponseStatus (value =  HttpStatus.CREATED)
    public MemberResponse createMember(@RequestBody @Valid MemberCreateRequest request){
           return MemberResponse.convertToMemberResponse(
                   memberService.createMember(request.convertToMember())
           );
    }

    @GetMapping("/member/{identityNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponse retrieveMember(@PathVariable Long identityNumber){
        return MemberResponse.convertToMemberResponse(
                memberService.retrieveMember(identityNumber)
        );
    }

    @PutMapping("/member")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponse updateMember(@RequestBody @Valid MemberUpdateRequest request ){
        return MemberResponse.convertToMemberResponse(
                memberService.updateMember(request.convertToMember())
        );
    }

    @DeleteMapping("/member/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable  Long memberId){
        memberService.deleteMember(memberId);
    }

}
