import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { POST_CREATE_NOT_ENOUGH_INFORMATION } from '../../constants/Messages';
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
  const { id } = useParams('id');
  const { post } = useGetPost(id);
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [body, setBody] = useState('');
  const [seriesId, setSeriesId] = useState(0);
  const [image, setImage] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    setTitle(post.title);
    setCategory(post.category);
    setDescription(post.description);
    setBody(post.content);
    setSeriesId(post.seriesId);
    setImage(post.imgUrls || []);
  }, [post]);

  const editPost = e => {
    e.preventDefault();
    const url = `posts/${id}`;
    const postData = {
      title,
      category,
      description,
      content: body,
    };

    if (image.length) postData.imgUrls = image;
    if (seriesId) postData.seriesId = seriesId;

    if (title && category && description && body) {
      console.log(postData);
      axios
        .patch(url, postData)
        .then(res => {
          console.log(res);
          navigate(`/posts/${category}/${res.data}`);
        })
        .catch(err => console.log(err));
    } else {
      alert(POST_CREATE_NOT_ENOUGH_INFORMATION);
    }
  };

  return (
    <Container>
      <PostCreateHeader title={title} setTitle={setTitle} curCategory={category} setCategory={setCategory} />
      <PostCreateDescription description={description} setDescription={setDescription} />
      <PostCreateBody body={body} setBody={setBody} setImage={setImage} />
      <PostCreateButtons submitNewPost={editPost} />
    </Container>
  );
};

export default PostEditPage;
