package com.ll.playon.domain.guild.guildJoinRequest.repository;

import com.ll.playon.domain.member.entity.Member;
import com.ll.playon.domain.guild.guild.entity.Guild;
import com.ll.playon.domain.guild.guildJoinRequest.entity.GuildJoinRequest;
import com.ll.playon.domain.guild.guildJoinRequest.enums.ApprovalState;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuildJoinRequestRepository extends JpaRepository<GuildJoinRequest, Long> {
    boolean existsByGuildAndMemberAndApprovalState(Guild guild, Member member, ApprovalState approvalState);
    List<GuildJoinRequest> findAllByGuildAndApprovalState(Guild guild, ApprovalState approvalState);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT gjr FROM GuildJoinRequest gjr WHERE gjr.id = :requestId")
    Optional<GuildJoinRequest> findByIdWithLock(@Param("requestId") Long requestId);
}
