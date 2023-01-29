import axios from 'axios';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import useGetPost from '../../hooks/useGetPost';
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

const PostEditPage = () => {
  const params = useParams('id');
  const { post } = useGetPost(params);
  const [title, setTitle] = useState(post.title);
  const [category, setCategory] = useState(post.category);
  const [description, setDescription] = useState(post.description);
  const [body, setBody] = useState(post.content);
  const navigate = useNavigate();

  const editPost = () => {
    const url = `posts/${params}`;
    const postData = {
      title,
      category,
      description,
      content: body,
    };

    if (post.seriesId) postData.seriesId = post.seriesId;

    if (title && category && description && body) {
      axios
        .patch(url, postData)
        .then(res => {
          console.log(res);
          navigate(`/posts/${category}/${res.data.id}`);
        })
        .catch(err => console.log(err));
    }
  };

  return (
    <Container>
      <PostCreateHeader title={title} setTitle={setTitle} curCategory={category} setCategory={setCategory} />
      <PostCreateDescription description={description} setDescription={setDescription} />
      <PostCreateBody body={body} setBody={setBody} />
      <PostCreateButtons submitNewPost={editPost} />
    </Container>
  );
};

export default PostEditPage;
