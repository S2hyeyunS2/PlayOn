package com.ll.playon.domain.guild.guildBoard.repository;

import com.ll.playon.domain.guild.guildBoard.entity.GuildBoard;
import com.ll.playon.domain.guild.guildBoard.entity.GuildBoardComment;
import com.ll.playon.domain.guild.guildMember.entity.GuildMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface GuildBoardCommentRepository extends JpaRepository<GuildBoardComment, Long> {
    List<GuildBoardComment> findByBoardOrderByCreatedAtAsc(GuildBoard board);
    List<GuildBoardComment> comment(String comment);
    void deleteByAuthor(GuildMember author);
    @Query("""
    SELECT gbc.board.id, COUNT(gbc.id)
    FROM GuildBoardComment gbc
    WHERE gbc.board.id IN :boardIds
    GROUP BY gbc.board.id
""")
    Map<Long, Long> countByBoardIds(@Param("boardIds") List<Long> boardIds);
}