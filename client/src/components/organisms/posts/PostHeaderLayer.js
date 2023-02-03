import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
import PostTitle from '../../molecules/posts/PostTitle';
import { UserInfoSmall } from '../../molecules/UserInfo';
import { LabelSmall } from '../../../styles/typo';
import useAuthStore from '../../../store/useAuthStore';

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
  const { currentUserId } = useAuthStore(state => state);

  const [title, setTitle] = useState('title');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('no description');
  const [writer, setWriter] = useState({
    id: 7,
    nickname: 'iamgroot',
    profileUrl: '',
  });
  const [createdAt, setCreatedAt] = useState('Not Created');
  const [modifiedAt, setModifiedAt] = useState('Not Modified');

  const navigate = useNavigate();

  const handleClickRemove = () => {
    const url = `posts/${post?.id}`;

    axios
      .delete(url)
      .then(res => {
        console.log(res);
        navigate(`/posts/`);
      })
      .catch(err => console.log(err));
  };
  useEffect(() => {
    setTitle(post.title);
    setCategory(post.category);
    setDescription(post.description);
    setWriter(post.writer);
    setCreatedAt(post.createdAt);
    setModifiedAt(post.modifiedAt);
  }, [post]);

  return (
    <Container>
      <PostTitle title={title} description={description} categoryName={category || 'Category'} />
      <DeatilInfoList>
        <List2>
          <UserInfoSmall id={writer?.id} name={writer?.nickname} image={writer?.profileUrl} />
          <List>
            작성일
            <CreatedAtText> {new Date().toDateString(createdAt)}</CreatedAtText>
          </List>
          <List>
            수정일
            <CreatedAtText> {new Date().toDateString(modifiedAt)}</CreatedAtText>
          </List>
        </List2>
        <List>
          {currentUserId === writer?.id ? (
            <>
              <Button type="button" to={`/posts/${post?.id}/edit`}>
                Edit
              </Button>
              <Button type="button" onClick={handleClickRemove}>
                Delete
              </Button>
            </>
          ) : (
            ''
          )}
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

const List2 = styled.div`
  display: flex;
  align-items: center;
  gap: 30px;
`;

const CreatedAtText = styled.div`
  color: var(--gray-400);
`;

const Button = styled(Link)`
  width: ${props => (props.width ? props.width : '51px')};
  height: ${props => (props.height ? props.height : '36px')};
  text-decoration: none;
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
