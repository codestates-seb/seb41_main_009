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
const PostHeaderLayer = ({ post }) => {
  const { title, desc, category, createdAt, modifiedAt } = post;
  // const { id, nickname, profileImageUrl } = writer;
  // TODO: 페이지 구현시 createAt, modifiedAt 은 Date 타입으로 받아 컴포넌트 내에서 변환하기
  console.log(desc);

  // UserInfoSmall 에 해당하는 props는 내려주면 오류가 뜸
  return (
    <Container>
      <PostTitle title={title} description="not found" categoryName={category || 'Category'} />
      <DeatilInfoList>
        <List>
          <UserInfoSmall />
          <CreatedAtText>createAt {new Date().toDateString(createdAt)}</CreatedAtText>
          <CreatedAtText>modifiedAt {new Date().toDateString(modifiedAt)}</CreatedAtText>
        </List>
        <List>
          <Button type="button">Edit</Button>
          <Button type="button">Delete</Button>
        </List>
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
const List = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const CreatedAtText = styled.div`
  color: var(--gray-400);
`;

const Button = styled.button`
  width: ${props => (props.width ? props.width : '51px')};
  height: ${props => (props.height ? props.height : '36px')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ffffff;
  border: none;
  color: black;
  ${LabelSmall}
  &:hover {
    background-color: var(--gray-50);
    cursor: pointer;
  }
`;
export default PostHeaderLayer;
