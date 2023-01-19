import styled from 'styled-components';
import PostTitle from '../../molecules/posts/PostTitle';
import { UserInfoSmall } from '../../molecules/UserInfo';
import { LabelSmall } from '../../../styles/typo';

/**
 * 포스트 상세페이지와 검색결과에 활용할수 있는 포스트헤더 organism
 * @param {string|number} id - 작성자의 id
 * @param {string} userName - 작성자의 닉네임
 * @param {string} userImage - 작성자의 이미지 URL
 * @param {string} title - 포스트의 타이틀
 * @param {string} desc - 포스트의 설명
 * @param {string} [categoryName] - 카테고리의 이름
 * @param {string} createdAt - 생성날짜
 * @param {string} modifiedAt - 수정날짜
 * @param {boolean} border - true 인경우 default 사용
 * @param {string} width - 컨테이너 넓이 고정설정
 * @param {string} height - 컨테이너 높이 고정설정
 * @returns {JSX.Element} -
 */
const PostHeaderLayer = ({
  id,
  userName,
  userImage,
  title,
  desc,
  categoryName,
  createdAt,
  modifiedAt,
  border,
  width,
  height,
}) => {
  // TODO: 페이지 구현시 createAt, modifiedAt 은 Date 타입으로 받아 컴포넌트 내에서 변환하기?
  return (
    <Container border={border} width={width} height={height}>
      <PostTitle title={title} description={desc} categoryName={categoryName || 'Category'} />
      <DeatilInfoList>
        <UserInfoSmall id={id} name={userName} image={userImage}>
          <CreatedAtText>{createdAt}</CreatedAtText>
          <CreatedAtText>{modifiedAt}</CreatedAtText>
        </UserInfoSmall>
        <IconList>
          <Icon>Icon</Icon>
          <Icon>Icon</Icon>
        </IconList>
      </DeatilInfoList>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  width: ${props => props.width || 'calc(100% / 12 * 10)'};
  border: ${props => (props.border ? '2px solid black' : 'none')};
  height: ${props => props.height || '193px'};
  box-sizing: content-box;
  flex-direction: column;
  padding: 29px 31px;
  gap: 10px;
`;

const DeatilInfoList = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
`;
const IconList = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;
const Icon = styled.div`
  ${LabelSmall}
`;

const CreatedAtText = styled.div`
  color: var(--gray-400);
`;

export default PostHeaderLayer;
