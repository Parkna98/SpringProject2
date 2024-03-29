package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;

public interface BoardMapper {
	/*<select id="boardListData" resultType="BoardVO" parameterType="int">
    SELECT no,subject,name,DATE_FORMAT(regdate,'%Y-%m-%d) as dbday, hit
    FROM board
    ORDER BY no DES 
    LIMIT #{start}
	</select>
	*/
	public List<BoardVO> boardListData(int start);
	/*
	  <select id="boardTotalPage" resultType="int">
	    SELECT CEIL(COUNT(*)/10.0) FROM board
	  </select>
	  */
	public int boardTotalPage();
	/*
	 * <insert id="boardInsert" parameterType="BoardVO">
	     INSERT INTO board(name,subject,content,pwd) 
	     VALUES(#{name},#{subject},#{content},#{pwd})
	   </insert>
	 */
	public void boardInsert(BoardVO vo);
	/*
	 * <update id="hitIncrement" parameterType="int">
	     UPDATE board SET hit=hit+1 
	     WHERE no=#{no}
	   </update>
	*/
	public void hitIncrement(int no);
	/*
	   <select id="boardDetailData" resultType="BoardVO" parameterType="int">
	   	 SELECT no,name,subject,content,DATE_FORMAT(regdate,'%Y-%m-%d') as dbday,hit 
	   	 FROM board WHERE no=#{no}
	   </select>
	 */
	public BoardVO boardDetailData(int no);
	/*
	 * <select id="boardGetPassword" resultType="string" parameterType="int">
	     SELECT pwd FROM board 
	     WHERE no=#{no} 
	   </select>
	 */
	public String boardGetPassword(int no);
	/*
	   <update id="boardUpdate" parameterType="BoardVO">
	     UPDATE board SET name=#{name},subject=#{subject},content=#{content}
	     WHERE no=#{no}
	   </update>
	 */
	public void boardUpdate(BoardVO vo);
}
