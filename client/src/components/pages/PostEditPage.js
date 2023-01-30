import axios from 'axios';
import { useEffect, useState } from 'react';
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
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [body, setBody] = useState('');
  const [seriesId, setSeriesId] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    setTitle(post.title);
    setCategory(post.category);
    setDescription(post.description);
    setBody(post.content);
    setSeriesId(post.seriesId);
  }, [post]);

  const editPost = () => {
    const url = `posts/${params}`;
    const postData = {
      title,
      category,
      description,
      content: body,
    };

    if (seriesId) postData.seriesId = seriesId;

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
