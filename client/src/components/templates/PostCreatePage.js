import { useState } from 'react';
import styled from 'styled-components';
import PostCreateBody from '../organisms/postcreate/PostCreateBody';
import PostCreateButtons from '../organisms/postcreate/PostCreateButtons';
import PostCreateDescription from '../organisms/postcreate/PostCreateDescription';
import PostCreateHeader from '../organisms/postcreate/PostCreateHeader';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 100%;
  align-items: center;
`;

const PostCreatePage = () => {
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [body, setBody] = useState('내용을 입력해주세요.');

  const submitNewPost = () => {
    const postData = {
      title,
      category,
      description,
      body,
    };

    if (title && category && description && body) {
      console.log(postData);
    }
  };

  return (
    <Container>
      <PostCreateHeader title={title} setTitle={setTitle} curCategory={category} setCategory={setCategory} />
      <PostCreateDescription description={description} setDescription={setDescription} />
      <PostCreateBody body={body} setBody={setBody} />
      <PostCreateButtons submitNewPost={submitNewPost} />
    </Container>
  );
};

export default PostCreatePage;
